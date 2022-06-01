import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { GlobalComponent } from "src/app/global-component";
import { AuthService } from "src/app/services/auth/auth.service";

@Component({
  selector: "app-home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.scss"],
})
export class HomeComponent implements OnInit {
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private auth: AuthService
  ) {}

  // global styling - make sure to add to top of page:
  // import { GlobalComponent } from "src/app/global-component";
  gridItemClass = GlobalComponent.gridItemClass;
  rippleColor = GlobalComponent.rippleColor;
  customRounding = GlobalComponent.customRounding;

  ngOnInit(): void {}
}
