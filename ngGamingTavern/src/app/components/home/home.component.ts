import { UserService } from 'src/app/services/user/user.service';
import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { User } from "src/app/models/user/user";
import { AuthService } from "src/app/services/auth/auth.service";

@Component({
  selector: "app-home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.scss"],
})
export class HomeComponent implements OnInit {

  submitted = false;
  isUsernameTaken: boolean = false;
  user: User;
  isUserLoaded: boolean;
  newUser: User = new User();
  

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private auth: AuthService,
    
  ) {}

  // add extra class styling here (i.e. mb-5, me-2, text-muted, bootstrap classes, etc)
  gridItemClass: string = "";

  ngOnInit(): void {}



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
}

}
