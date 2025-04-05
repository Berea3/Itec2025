import { Component } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {LinkService} from '../../services/link.service';
import {Event} from '../../entities/Event';
import {HeaderComponent} from '../header/header.component';

@Component({
  selector: 'app-calendar',
    imports: [
        HeaderComponent
    ],
  templateUrl: './calendar.component.html',
  styleUrl: './calendar.component.css'
})
export class CalendarComponent {

    events: Event[];

    constructor(private http: HttpClient, private link: LinkService) {}

    ngOnInit()
    {
        this.http.get(this.link.url+"/events/getEventsByUser").subscribe(
            (response: any)=>{
                this.events=response;
                this.events.sort((a, b) => new Date(b.date).getTime() - new Date(a.date).getTime());
            }
        )
    }
}
