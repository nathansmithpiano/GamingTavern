import { Timezone } from "./../timezone/timezone";
export class Location {
  id: number;
  timezone: Timezone;
  name: string;
  street: string;
  city: string;
  state: string;
  zip: string;
  country: string;
  created: string;
  updated: string;

  constructor(
    id: number = 0,
    timezone: Timezone = new Timezone(),
    name: string = "",
    street: string = "",
    city: string = "",
    state: string = "",
    zip: string = "",
    country: string = "",
    created: string = "",
    updated: string = ""
  ) {
    this.id = id;
    this.timezone = timezone;
    this.name = name;
    this.street = street;
    this.city = city;
    this.state = state;
    this.zip = zip;
    this.country = country;
    this.created = created;
    this.updated = updated;
  }
}
