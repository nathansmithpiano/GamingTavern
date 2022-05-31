import { DatePipe } from '@angular/common';
export class Meetup {
  id: number;
  name: string;
  date: string;
  capacity: number;
  description: string;
  created: string;
  updated: string;
  private datePipe: DatePipe;

  constructor(
    id: number = 0,
    name: string = "",
    date: string = "",
    capacity: number = 0,
    description: string = "",
    created: string = "",
    updated: string = ""
  ) {
    this.id = id;
    this.name = name;
    this.date = date;
    this.capacity = capacity;
    this.description = description;
    this.created = created;
    this.updated = updated;
  }
}
