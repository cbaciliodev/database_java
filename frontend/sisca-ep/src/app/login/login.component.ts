import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';

declare function init_plugins();

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(public _router: Router) { }

  forma:FormControl

  ngOnInit() {

    init_plugins();

    
  }

  ingresar(){
  this._router.navigate(['/dashboard']);
  }

}
