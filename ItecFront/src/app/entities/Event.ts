import {User} from './User';

export class Event{
    id: string;

    name: string;
    curricula: string;

    date: Date;

    users: User[]=[]
}
