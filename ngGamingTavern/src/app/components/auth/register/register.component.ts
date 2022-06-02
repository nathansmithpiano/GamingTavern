import { UserService } from 'src/app/services/user/user.service';
import { User } from 'src/app/models/user/user';
import { Router } from '@angular/router';
import { AuthService } from './../../../services/auth/auth.service';
import { Component, OnInit } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { CustomvalidationService } from 'src/app/servicescustomvalidation.service';
import { GlobalComponent } from 'src/app/global-component';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  submitted = false;
  isUsernameTaken: boolean = false;
  user: User;
  isUserLoaded: boolean;

  constructor(
    private auth: AuthService,
    private router: Router,
    private customValidator: CustomvalidationService,
    private userService: UserService,
  ) { }

  rippleColor = GlobalComponent.rippleColor;
  gridItemClass = GlobalComponent.gridItemClass;
  customRounding = GlobalComponent.customRounding;

  newUser: User = new User();

  ngOnInit() {}


  register(user: User): void {
    console.log('Registering user:');
    console.log(user);
    this.auth.register(user).subscribe({
      next: (registeredUser) => {
        this.auth.login(user.username, user.password).subscribe({
          next: (loggedInUser) => {
            this.router.navigateByUrl('/users/' + user.username);
          },
          error: (problem) => {
            console.error('RegisterComponent.register(): Error logging in user:');
            console.error(problem);
          }
        });
      },
      error: (fail) => {
        console.error('RegisterComponent.register(): Error registering account');
        console.error('user account already taken')
        this.isUsernameTaken = true;
        console.error(fail);
      }
    });

  
    function checkUsername(username: string) {
      if(this.getUserByUsername(username)){
        this.isUsernameTaken = true;
      }
      
    }
    
function username(username: any, string: any) {
  throw new Error('Function not implemented.');
}
}}