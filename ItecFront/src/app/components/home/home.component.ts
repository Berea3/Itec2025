import { Component } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {LinkService} from '../../services/link.service';
import {HeaderComponent} from '../header/header.component';
import {Event} from '../../entities/Event'
import {SecurityService} from '../../services/security.service';
import {Router} from '@angular/router';
import {User} from '../../entities/User';

@Component({
  selector: 'app-home',
    imports: [
        HeaderComponent
    ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

    events: Event[];
    event: Event | undefined;
    eventUsers: User[]=[];

    constructor(private http: HttpClient, private link: LinkService, private securityService: SecurityService, private router: Router) {}

    ngOnInit()
    {
        this.http.get(this.link.url+"/events/getAll").subscribe(
            (response: any)=>{
                this.events=response;
                if (this.securityService.isLoggedIn() && this.securityService.getUser().preference!=undefined) this.sortByPreference();
            }
        )
    }

    signUpToEvent(eventId: string)
    {
        this.http.put(this.link.url+"/events/sign-up",eventId).subscribe();
    }

    openEvent(eventId: string)
    {
        this.http.get(this.link.url+"/events/getUsersByEventId/"+eventId).subscribe(
            (response: any)=>{
                this.eventUsers=response;
                if (this.events.find(event=>event.id==eventId)!=undefined)
                {
                    this.event=this.events.find(event=>event.id==eventId);
                    if (this.event!=undefined) {
                        if (this.event.organizerId == this.securityService.getUser().id && this.securityService.getUser().id!=undefined)
                        {
                            console.log("first");
                            console.log(this.event.organizerId);
                            console.log(this.securityService.getUser().id);
                            this.router.navigateByUrl("/event/" + eventId);
                        }
                    }
                }
                if (this.eventUsers.find(user=>user.id==this.securityService.getUser().id))
                {
                    console.log("bruh brah");
                    this.router.navigateByUrl("/event/" + eventId);
                }
            }
        )
    }

    sortByPreference()
    {
        console.log("sa moara");
        let preference=this.securityService.getUser().preference;
        let count: number=0;
        for (let i: number=0;i<this.events.length;i++)
        {
            if (this.events[i].category===preference) if (count<3)
            {
                this.swapElements(count,i);
                count++;
            }
        }
    }

    swapElements(i: number, j:number)
    {
        let temp=this.events[i];
        this.events[i]=this.events[j];
        this.events[j]=temp;
    }
}
