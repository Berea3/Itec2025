import { Routes } from '@angular/router';
import {HomeComponent} from './components/home/home.component';
import {LoginComponent} from './components/security/login/login.component';
import {SignUpComponent} from './components/security/sign-up/sign-up.component';
import {AddEventComponent} from './components/add-event/add-event.component';
import {EventViewComponent} from './components/event-view/event-view.component';
import {CalendarComponent} from './components/calendar/calendar.component';
import {authGuard} from './services/auth.guard';

export const routes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'login', component: LoginComponent },
    { path: 'sign-up', component: SignUpComponent },
    { path: 'add-event', component: AddEventComponent, canActivate: [authGuard]},
    { path: 'event/:id', component: EventViewComponent, canActivate: [authGuard]},
    { path: 'calendar', component: CalendarComponent, canActivate: [authGuard] },
];
