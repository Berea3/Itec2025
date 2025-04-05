import { Component } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {LinkService} from '../../services/link.service';
import {HeaderComponent} from '../header/header.component';
import {Event} from '../../entities/Event'

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

    constructor(private http: HttpClient, private link: LinkService) {}

    ngOnInit()
    {
        this.http.get(this.link.url+"/events/getAll").subscribe(
            (response: any)=>{
                this.events=response;
            }
        )
    }

    signUpToEvent()
    {
        this.http.
    }
}
