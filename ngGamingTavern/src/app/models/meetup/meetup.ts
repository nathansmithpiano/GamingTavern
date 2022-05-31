export class Meetup {
  id: number;
  enabled: boolean;
  name: string;
  date: string;
  capacity: number;
  description: string;
  created: string;
  updated: string;

  constructor(
    id: number = 0,
    enabled: boolean,
    name: string = "",
    date: string = "",
    capacity: number = 0,
    description: string = "",
    created: string,
    updated: string
  ) {
    this.id = id;
    this.enabled = enabled;
    this.name = name;
    this.date = date;
    this.capacity = capacity;
    this.description = description;
    this.created = created;
    this.updated = updated;
  }
}
