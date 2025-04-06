import { Component } from '@angular/core';
import {Event} from '../../entities/Event'
import {HttpClient} from '@angular/common/http';
import {LinkService} from '../../services/link.service';
import {Router} from '@angular/router';

import {ChangeDetectionStrategy} from '@angular/core';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {provideNativeDateAdapter} from '@angular/material/core';
import {FormsModule} from '@angular/forms';
import {MatIcon} from '@angular/material/icon';
import {MatTooltip} from '@angular/material/tooltip';
import {HeaderComponent} from '../header/header.component';


@Component({
  selector: 'app-add-event',
    imports: [
        MatDatepickerModule, MatInputModule, MatFormFieldModule, FormsModule, MatIcon, HeaderComponent
    ],
  templateUrl: './add-event.component.html',
  styleUrl: './add-event.component.css'
})
export class AddEventComponent {
    event: Event=new Event();

    constructor(private http: HttpClient, private link: LinkService, private router: Router) {}

    onChangeEventDate(date: Date)
    {
        //@ts-ignore
        this.event.date=date['value'];
        this.event.date=new Date(this.event.date.getTime() - this.event.date.getTimezoneOffset()*60000);
    }

    onCreate(){
        this.http.post(this.link.url+"/events/create",this.event).subscribe();
        this.router.navigateByUrl("");
    }
}
