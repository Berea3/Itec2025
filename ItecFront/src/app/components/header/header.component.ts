import { Component } from '@angular/core';
import {RouterLink} from '@angular/router';
import {SecurityService} from '../../services/security.service';

@Component({
  selector: 'app-header',
    imports: [
        RouterLink
    ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {

    constructor(private securityService: SecurityService) {}

    isOrganizer()
    {
        if (this.securityService.getRole()=="organizer") return true;
        console.log("not organizer");
        console.log(this.securityService.getRole());
        return false;
        // if (sessionStorage.getItem("loggedin")=="yes") return true;
        // else return false;
    }
}
