import {User} from './User';

export class Event{
    id: string;

    name: string;
    curricula: string;

    date: Date;
    organizerId: string;
    chat: string;
    aiSummary: string;

    users: User[]=[]
}
