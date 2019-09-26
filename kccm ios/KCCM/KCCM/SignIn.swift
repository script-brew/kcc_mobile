//
//  ViewController.swift
//  KCCM
//
//  Created by 김용호 on 08/08/2019.
//  Copyright © 2019 kcc. All rights reserved.
//

// 로그인 화면

import UIKit
import Firebase

class SignIn: UIViewController {
    
    @IBOutlet weak var emailTextField: UITextField!
    @IBOutlet weak var pwTextField: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }
    
    
    @IBAction func signInButton(_ sender: Any) {
        Auth.auth().signIn(withEmail: emailTextField.text!, password: pwTextField.text!){
            (user,error) in
            if user != nil{
                print("success")
            }
            else{
                print("fail")
            }
        }
    }
    
}

