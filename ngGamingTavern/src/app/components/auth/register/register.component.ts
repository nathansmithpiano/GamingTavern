import { Router } from '@angular/router';
import { AuthService } from './../../../services/auth/auth.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user/user';
import { catchError, Observable, throwError } from 'rxjs';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  constructor(
    private auth: AuthService,
    private router: Router
  ) { }

  newUser: User = new User();

  ngOnInit(): void {
  }

  register(user: User): void {
    console.log('Registering user:');
    console.log(user);
    this.auth.register(user).subscribe({
      next: (registeredUser) => {
        this.auth.login(user.username, user.password).subscribe({
          next: (loggedInUser) => {
            this.router.navigateByUrl('/user/1');
          },
          error: (problem) => {
            console.error('RegisterComponent.register(): Error logging in user:');
            console.error(problem);
          }
        });
      },
      error: (fail) => {
        console.error('RegisterComponent.register(): Error registering account');
        console.error(fail);
      }
    });
  }

  // getUserByUsername(username: string): Observable<User> {
  //   return this.http
  //     .get<User>(this.url2 + username, {
  //       headers: { Authorization: "Basic " + this.auth.getCredentials() },
  //     })
  //     .pipe(
  //       catchError((err: any) => {
  //         console.log(err);
  //         return throwError("KABOOM");
  //       })
  //     );
  // }

}
