export class Equipment {

    id: number;
    name: string;
    model: string;
    description: string;

    constructor(id: number=0, name: string='', model: string='', description: string=''){
        this.id = id;
        this.name = name;
        this.model = model;
        this.description = description;
    }
}
