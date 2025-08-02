import { Component } from '@angular/core';
import { Activityservice } from '../../service/activityservice';
import { ChartConfiguration } from 'chart.js';
import { BaseChartDirective } from 'ng2-charts';
import { CommonModule } from '@angular/common';

export interface StatsDTO {
  totalCalories: number;
  totalDuration: number;
  totalDistance: number;
  totalSteps: number;
  achievedGoals: number;
  notAchievedGoals: number;
}

@Component({
  selector: 'app-dashboard',
  imports: [BaseChartDirective, CommonModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})
export class Dashboard {
   stats: StatsDTO | null = null;

  workoutChartData: ChartConfiguration<'line'>['data'] = {
    labels: [],
    datasets: []
  };

  activityChartData: ChartConfiguration<'line'>['data'] = {
    labels: [],
    datasets: []
  };

  constructor(private dashboardService: Activityservice) {}

  ngOnInit(): void {
    this.loadStats();
    this.loadWorkoutChart();
    this.loadActivityChart();
  }

  loadStats() {
    this.dashboardService.getStats().subscribe(data => this.stats = data);
  }
loadWorkoutChart() {
  this.dashboardService.getRecentWorkouts().subscribe((data: any[]) => {
    this.workoutChartData = {
      labels: data.map((w: any) => w.date),
      datasets: [
        {
          data: data.map((w: any) => w.calories),
          label: 'Calories Burned',
          borderColor: 'green',
          fill: false
        },
        {
          data: data.map((w: any) => w.duration),
          label: 'Duration',
          borderColor: 'blue',
          fill: false
        }
      ]
    };
  });
}


loadActivityChart() {
  this.dashboardService.getRecentActivities().subscribe((data: any[]) => {
    this.activityChartData = {
      labels: data.map((a: any) => a.date),
      datasets: [
        {
          data: data.map((a: any) => a.calories),
          label: 'Calories Burned',
          borderColor: 'red',
          fill: false
        },
        {
          data: data.map((a: any) => a.steps),
          label: 'Steps',
          borderColor: 'orange',
          fill: false
        },
        {
          data: data.map((a: any) => a.distance),
          label: 'Distance',
          borderColor: 'magenta',
          fill: false
        }
      ]
    };
  });
}
}
