import { Meetup } from 'src/app/models/meetup/meetup';
import { Component, OnInit } from '@angular/core';
import { MeetupService } from 'src/app/services/meetup/meetup.service';

@Component({
  selector: 'app-meetup',
  templateUrl: './meetup.component.html',
  styleUrls: ['./meetup.component.scss']
})
export class MeetupComponent implements OnInit {

  constructor(private meetupSvc: MeetupService) { }

  selected: Meetup | null = null;

  meetups: Meetup[] = [];
  
  leftColumnWidth = 4;
  
  topPadding = 3;
  
  rightColumnWidth = 12 - this.leftColumnWidth;

  leftHeader = 'Meetups';
  
  meetupName = '';
  meetupDate = '';
  meetupCapacity = 0;
  meetupDescription = '';
  meetupCreated = '';
  meetupUpdated = '';
  

  ngOnInit(): void {
    this.reload();
  }

  reload = (): void => {
    this.meetupSvc.index().subscribe(
      data => this.meetups = data,
      err => console.error(err)
    );
     }

}
