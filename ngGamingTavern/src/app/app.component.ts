import { Component } from "@angular/core";
import { GlobalComponent } from "./global-component";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"],
})
export class AppComponent {
  title = "Gaming Tavern";

  gridItemClass = GlobalComponent.gridItemClass;
  rippleColor = GlobalComponent.rippleColor;
  customRounding = GlobalComponent.customRounding;
}
