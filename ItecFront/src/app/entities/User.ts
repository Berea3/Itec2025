import {Event} from './Event';

export class User{
    id: string;

    email: string;
    password: string;

    events: Event[]=[];
}
