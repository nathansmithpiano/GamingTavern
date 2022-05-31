import { Game } from "./../../models/game/game";
import { Component, OnInit } from "@angular/core";
import { GameService } from "src/app/services/game/game.service";


@Component({
  selector: "app-game",
  templateUrl: "./game.component.html",
  styleUrls: ["./game.component.scss"],
})
export class GameComponent implements OnInit {
  constructor(private gameSvc: GameService) {}

  selected: Game | null = null;

  games: Game[] = [];

  leftColumnWidth = 4;

  topPadding = 3;

  rightColumnWidth = 12 - this.leftColumnWidth;

  leftHeader = "Games";

  gameEnabled = true;
  gameName = "";
  gameStudio = "";
  gameImage_url = "";
  gameUrl = "";
  gameCreated = "";
  gameUpdated = "";

  ngOnInit(): void {
    this.reload();
  }

  reload = (): void => {
    this.gameSvc.index().subscribe(
      (data) => (this.games = data),
      (err) => console.error(err)
    );
  };
}
