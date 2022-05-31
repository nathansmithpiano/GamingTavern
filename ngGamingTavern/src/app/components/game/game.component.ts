import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent implements OnInit {

  constructor(private datePipe: DatePipe) { }

  ngOnInit(): void {
    this.tempThing();
  }

  leftColumnWidth = 4;
  topPadding = 3;
  rightColumnWidth = 12 - this.leftColumnWidth;

  leftHeader = 'Main Title';
  
  collection: string[] = [];
  
  tempThing = (): void => {
    let numItems = 30;
    for (let i = 0; i < numItems; i++) {
      this.collection.push('item ' + (i+1));
    }

  };

  

  name = 'Game Name';
  description = 'This is a description of a single game.  It may be long or short.';
  imageUrl = 'https://cdn.cloudflare.steamstatic.com/steam/apps/1644960/header.jpg?t=1653059048';
  created = this.datePipe.transform('2022-05-24 18:30:00', 'short');


}
