import { User } from 'src/app/models/user/user';
export class Alias {
  id: number;
  username: string;
  user: User;
  enabled: boolean;
  name: string;
  description: string;
  imageUrl: string;
  created: string;
  updated: string;

  constructor(
    id: number = 0,
    username: string = "",
    user: User,
    enabled: boolean = true,
    name: string = "",
    description: string = "",
    imageUrl: string = "",
    created: string = "",
    updated: string = ""
  ) {
    this.id = id;
    this.username = username;
    this.user = user;
    this.enabled = enabled;
    this.name = name;
    this.description = description;
    this.imageUrl = imageUrl;
    this.created = created;
    this.updated = updated;
  }
}
