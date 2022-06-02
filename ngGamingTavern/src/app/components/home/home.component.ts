import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { GlobalComponent } from "src/app/global-component";
import { AuthService } from "src/app/services/auth/auth.service";
import {
  AbstractControl,
  FormControl,
  FormGroup,
  Validators,
} from "@angular/forms";

@Component({
  selector: "app-home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.scss"],
})
export class HomeComponent implements OnInit {
  validationForm: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private auth: AuthService
  ) {
    this.validationForm = new FormGroup({
      firstName: new FormControl(null, Validators.required),
      lastName: new FormControl(null, Validators.required),
      email: new FormControl(null, Validators.required)
    });
  }

  // global styling - make sure to add to top of page:
  // import { GlobalComponent } from "src/app/global-component";
  gridItemClass = GlobalComponent.gridItemClass;
  rippleColor = GlobalComponent.rippleColor;
  customRounding = GlobalComponent.customRounding;

  get firstName(): AbstractControl {
    return this.validationForm.get("firstName")!;
  }

  get lastName(): AbstractControl {
    return this.validationForm.get("lastName")!;
  }

  get email(): AbstractControl {
    return this.validationForm.get("email")!;
  }

  ngOnInit(): void {}
}
