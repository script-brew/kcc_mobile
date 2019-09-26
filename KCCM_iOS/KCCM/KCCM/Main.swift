//
//  ViewController3.swift
//  KCCM
//
//  Created by 김용호 on 10/08/2019.
//  Copyright © 2019 kcc. All rights reserved.
//

// 메인 화면
import Foundation
import UIKit

class Main: UIViewController {
    
    var menuShowing = false
    
    
    @IBOutlet weak var LeadingConstraint: NSLayoutConstraint!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }
    
    @IBAction func openMenu(_ sender: Any) {
        if(menuShowing){
            LeadingConstraint.constant = -130
        }
        else{
            LeadingConstraint.constant = 0
            UIView.animate(withDuration: 0.2, delay: 0.0, options: .curveEaseIn, animations: {
                self.view.layoutIfNeeded()
            })
        }
        menuShowing = !menuShowing
    }
}
//
