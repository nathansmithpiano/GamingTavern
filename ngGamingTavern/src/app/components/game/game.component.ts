import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  leftCardWidth = 4;
  topPadding = 3;
  rightColumnWidth = 12 - this.leftCardWidth;
  rightColumn1Card1Width = 3;
  rightColumn1Card2Width = 5 - this.rightColumn1Card1Width;
  leftSummaryClass = 'text-center text-muted';

}
