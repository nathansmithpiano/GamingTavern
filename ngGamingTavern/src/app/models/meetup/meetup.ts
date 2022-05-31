<<<<<<< HEAD
import { DatePipe } from '@angular/common';
export class Meetup {
  id: number;
=======
export class Meetup {
  id: number;
  enabled: boolean;
>>>>>>> ac5768c592865b39d9a76f056b99d8afbc521c3a
  name: string;
  date: string;
  capacity: number;
  description: string;
  created: string;
  updated: string;
<<<<<<< HEAD
  private datePipe: DatePipe;

  constructor(
    id: number = 0,
=======

  constructor(
    id: number = 0,
    enabled: boolean,
>>>>>>> ac5768c592865b39d9a76f056b99d8afbc521c3a
    name: string = "",
    date: string = "",
    capacity: number = 0,
    description: string = "",
<<<<<<< HEAD
    created: string = "",
    updated: string = ""
  ) {
    this.id = id;
=======
    created: string,
    updated: string
  ) {
    this.id = id;
    this.enabled = enabled;
>>>>>>> ac5768c592865b39d9a76f056b99d8afbc521c3a
    this.name = name;
    this.date = date;
    this.capacity = capacity;
    this.description = description;
    this.created = created;
    this.updated = updated;
  }
}
