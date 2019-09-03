// jshint esversion: 8
// Runtime: Node.js 8
// firebase --version: 7.2.4

// The Cloud Functions for Firebase SDK to create Cloud Functions and setup triggers.
const functions = require('firebase-functions');

// The Firebase Admin SDK to access the Firebase Realtime Database.
const admin = require('firebase-admin');
admin.initializeApp();

const db = admin.firestore();

const json2excel = require('js2excel');

// Create and Deploy Your First Cloud Functions
// https://firebase.google.com/docs/functions/write-firebase-functions

exports.helloWorld = functions.https.onRequest((request, response) => {
    response.send("Hello from Firebase!");
});


exports.signin = functions.https.onRequest((request, response) => {
    var data = JSON.parse(Object.keys(request.body)[0]);    // get request data, parse
    var email = data.user_info.email;
    var password = data.user_info.password;

    var status_code = 400;     // create default response query
    db.collection("UserInfo").get().then((snapshot) => {
        snapshot.forEach((doc) => {
            if ( doc.email === email )    // if id matches
            {
                if ( doc.password === password )    // if password matches
                    status_code = 200;    // change status code
                
                if ( doc.password !== password )    // if password not matches 
                    status_code = 201;    // change status code
            }
        });
        console.log("status_code: ", status_code);
        response.send(status_code);    // send response query to https

        return;    // return Promise
    }).catch((err) => {
        console.log("Error getting documents", err);    // if Error occurs, go to Firebase\functions\log
    });
});


exports.signup = functions.https.onRequest((request, response) => {
    var data = JSON.parse(Object.keys(request.body)[0]);    // get request data, parse
    var email = data.user_info.email;
    var password = data.user_info.password;
    var name = data.user_info.name;
    var birthday = data.user_info.birthday;
    var uid = data.user_info.uid;

    var status_code = 400;    // create default response query
    db.collection("UserInfo").get().then((snapshot) => {
        snapshot.forEach((doc) => {
            if ( doc.data().email === email )    // if email already exists in Database
            {
                console.log("email overlapped: ", email);
                status_code = 202;
                console.log("status_code: ", status_code);
                response.send(status_code);
            }
        }); // end forEach
        
        return;    // return Promise
    }).catch((err) => {
        console.log("Error getting documents", err);    // if Error occurs, go to Firebase\functions\log
    });

    // update data on Database
    db.collection('UserInfo').doc().set({
        "email": email,
        "password": password,
        "name": name,
        "birthday": birthday,
        "uid": uid
    });
    status_code = 200;    // change status code
    console.log("status_code: ", status_code);
    
    response.send(status_code);    // send response query to https
});


exports.JSON_to_xlsx_converter = functions.https.onRequest((request, response) => {
    var status_code = 400;    // create default response query
    let table = [];    // excel's data will be exported, which you probably get it from server.
    db.collection("UserInfo").get().then((snapshot) => {
        snapshot.forEach((doc) => {
            var data = doc.data();
            var name = data.name;
            var birthday = data.birthday;
            var email = data.email;
            table.push({
                "name": name,
                "birthday": birthday,
                "email": email
            });
            status_code = 200;
        });    // end forEach

        // this will export a excel and the file's name is user-info-data.xlsx
        // the default file's name is excel.xlsx
        try
        {
            json2excel({
                table,
                name: 'user-info-data',
                formatDate: 'yyyy/mm/dd'
            });
        }
        catch (e)
        {
            console.error("export error");
        }

        console.log("export success");
        response.send(status_code);    // send response query to https
        return;    // return Promise
    }).catch((err) => {
        console.log("Error getting documents", err);    // if Error occurs, go to Firebase\functions\log
    });
});
