<<<<<<< HEAD
export class Game {
    id: number;
    enabled: boolean;
    name: string;
    studio: string;
    image_url: string;
    url: string;
    created: string;
    updated: string;

    constructor(
        id: number=0,
    enabled: boolean=true,
    name: string = "",
    studio: string = "",
    image_url: string = "",
    url: string = "",
    created: string = "",
    updated: string = "",
    ){
        this.id = id;
        this.enabled = enabled;
        this.name = name;
        this.studio = studio;
        this.image_url = image_url;
        this.url = url;
        this.created = created;
        this.updated = updated;
    }
=======
import { Rating } from "../rating/rating";

export class Game {
  id: number;
  rating: Rating;
  enabled: boolean;
  name: string;
  studio: string;
  imageUrl: string;
  created: string;
  updated: string;

  constructor(
    id: number = 0,
    rating: Rating,
    enabled: boolean = true,
    name: string = "",
    studio: string = "",
    imageUrl: string = "",
    created: string = "",
    updated: string = ""
  ) {
    this.id = id;
    this.rating = rating;
    this.enabled = enabled;
    this.name = name;
    this.studio = studio;
    this.imageUrl = imageUrl;
    this.imageUrl = imageUrl;
    this.created = created;
    this.updated = updated;
  }
>>>>>>> ac5768c592865b39d9a76f056b99d8afbc521c3a
}
