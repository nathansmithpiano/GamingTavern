export class Alias {
  id: number;
  username: string;
  enabled: boolean;
  name: string;
  description: string;
  imageUrl: string;
  created: string;
  updated: string;

  constructor(
    id: number = 0,
    username: string = "",
    enabled: boolean = true,
    name: string = "",
    description: string = "",
    imageUrl: string = "",
    created: string = "",
    updated: string = ""
  ) {
    this.id = id;
    this.username = username;
    this.enabled = enabled;
    this.name = name;
    this.description = description;
    this.imageUrl = imageUrl;
    this.created = created;
    this.updated = updated;
  }
}
