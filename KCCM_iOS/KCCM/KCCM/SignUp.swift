//
//  ViewController2.swift
//  KCCM
//
//  Copyright © 2019 kcc. All rights reserved.
//
// sign-up view

import Foundation
import Firebase
import UIKit

class SignUp: UIViewController {
    
    let db = Firestore.firestore()
    
    // ID TextField
    @IBOutlet weak var emailTextField: UITextField!
    
    // PW TextField
    @IBOutlet weak var passwordTextField: UITextField!
    
    // Name TextField
    @IBOutlet weak var nameTextField: UITextField!
    
    // Birthday TextField
    @IBOutlet weak var birthdayTextField: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }
    
    
    // OK(Korean) Button Click
    @IBAction func SignUpButton(_ sender: Any) {
        // Create user
        Auth.auth().createUser(withEmail: emailTextField.text!, password: passwordTextField.text!){
            (user, error) in
            
            //Create user succeeded
            if error == nil{
                print("good")
                
                // Get current UID
                guard let userID = Auth.auth().currentUser?.uid else{return}
                
                // Add Database
                var ref: DocumentReference? = nil
                ref = self.db.collection("UserInfo").addDocument(data: ["birthday": self.birthdayTextField.text!, "email": self.emailTextField.text!, "name": self.nameTextField.text!, "password": self.passwordTextField.text!, "uid": userID]){
                    err in
                    if let err = err{
                        print("Error adding document: \(err)")
                    }else{
                        print("Document added with ID: \(ref!.documentID)")
                    }
                }
            }
                
            // Create user failed
            else{
                print("bad")
            }
        }
        
    }
}
