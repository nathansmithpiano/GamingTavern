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
}
