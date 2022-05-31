import { DatePipe } from "@angular/common";

export class User {
  id: number;
  enabled: boolean;
  role: string;
  username: string;
  password: string;
  email: string;
  firstName: string;
  middleName: string;
  lastName: string;
  description: string;
  imageUrl: string;
  created: string;
  updated: string;

  constructor(
    id: number = 0,
    enabled: boolean = true,
    role: string = "",
    username: string = "",
    password: string = "",
    email: string = "",
    firstName: string = "",
    middleName: string = "",
    lastName: string = "",
    description: string = "",
    imageUrl: string = "",
    created: string = "",
    updated: string = ""
  ) {
    this.id = id;
    this.enabled = enabled;
    this.role = role;
    this.username = username;
    this.password = password;
    this.email = email;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.description = description;
    this.imageUrl = imageUrl;
    this.created = created;
    this.updated = updated;
  }
}
