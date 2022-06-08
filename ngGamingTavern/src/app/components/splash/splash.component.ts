import { Component, OnInit } from "@angular/core";

@Component({
  selector: "app-splash",
  templateUrl: "./splash.component.html",
  styleUrls: ["./splash.component.scss"],
})
export class SplashComponent implements OnInit {
  constructor() {}

  links: Array<string> = [
    "https://cdn.cloudflare.steamstatic.com/steam/apps/730/header.jpg?t=1641233427",
    "https://cdn.cloudflare.steamstatic.com/steam/apps/1599340/header.jpg?t=1652803265",
    "https://cdn.cloudflare.steamstatic.com/steam/apps/570/header.jpg?t=1650611880",
    "https://cdn.cloudflare.steamstatic.com/steam/apps/578080/header.jpg?t=1649663090",
    "https://cdn.cloudflare.steamstatic.com/steam/apps/1172470/header.jpg?t=1653320846",
  ];

  ngOnInit(): void {}
}
