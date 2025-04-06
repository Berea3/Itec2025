import {Event} from './Event';

export class User{
    id: string;

    email: string;
    password: string;
    preference: string;

    events: Event[]=[];
}
