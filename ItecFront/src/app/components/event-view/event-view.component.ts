import { Component } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {Event} from '../../entities/Event';
import {LinkService} from '../../services/link.service';
import {HeaderComponent} from '../header/header.component';
import {FormsModule} from '@angular/forms';
import {NgIf} from '@angular/common';
import {SecurityService} from '../../services/security.service';
import {User} from '../../entities/User';

@Component({
  selector: 'app-event-view',
    imports: [
        HeaderComponent,
        FormsModule,
        NgIf
    ],
  templateUrl: './event-view.component.html',
  styleUrl: './event-view.component.css'
})
export class EventViewComponent {

    event: Event;
    eventUsers: User[]=[];
    message: string;

    constructor(private http: HttpClient, private activatedRoute: ActivatedRoute, private link: LinkService, private securityService: SecurityService) {}

    ngOnInit()
    {
        this.http.get(this.link.url+"/events/getById/"+this.activatedRoute.snapshot.params['id']).subscribe(
            (response: any)=>{
                this.event=response;
            }
        )

        this.http.get(this.link.url+"/events/get")
    }

    updateCurriculum()
    {
        this.http.put(this.link.url+"/events/createCurricula/"+this.event.id, this.event.curricula).subscribe();
    }

    isOrganizer()
    {
        if (this.event.organizerId==this.securityService.getUser().id) return true;
        return false;
    }

    sendMessage()
    {
        this.http.put(this.link.url+"/events/send-message/"+this.event.id,this.message).subscribe();
    }
}
