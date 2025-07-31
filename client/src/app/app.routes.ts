import { Routes } from '@angular/router';
import { Dashboard } from './component/dashboard/dashboard';
import { Workout } from './component/workout/workout';
import { Activity } from './component/activity/activity';
import { Goal } from './component/goal/goal';

export const routes: Routes = [
      { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
    {
        path: "dashboard", component: Dashboard
    },
    {
        path: "workout", component: Workout
    },
    {
        path: "activity", component: Activity
    },
    {
        path: "goal", component:Goal
    }
];
