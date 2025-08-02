import { CommonModule, DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Activityservice } from '../../service/activityservice';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-goal',
  imports: [FormsModule, ReactiveFormsModule, DatePipe, CommonModule],
  templateUrl: './goal.html',
  styleUrl: './goal.css'
})
export class Goal implements OnInit{
  goalForm!: FormGroup;
  goals: any[] = [];

  constructor(
    private fb: FormBuilder,
    private activityService: Activityservice,
    private message: ToastrService
  ) {}

  ngOnInit(): void {
    this.goalForm = this.fb.group({
      description: [null, Validators.required],
      startDate: [null, Validators.required],
      endDate: [null, Validators.required]
    });

    this.loadGoals();
  }

  onSubmit() {
    if (this.goalForm.invalid) {
      this.message.warning('Vui lòng nhập đầy đủ thông tin!');
      return;
    }

    const goalData = this.goalForm.value;

    this.activityService.postGoal(goalData).subscribe({
      next: () => {
        this.message.success('Đã tạo mục tiêu thành công!');
        this.goalForm.reset();
        this.loadGoals();
      },
      error: (err) => {
        this.message.error('Lỗi khi tạo mục tiêu!');
        console.error(err);
      }
    });
  }

  loadGoals() {
    this.activityService.getGoals().subscribe({
      next: (res) => (this.goals = res),
      error: (err) => console.error('Lỗi tải mục tiêu:', err)
    });
  }

  markAsCompleted(id: number) {
    this.activityService.markGoalAsCompleted(id).subscribe({
      next: () => {
        this.message.success('Đã đánh dấu hoàn thành!');
        this.loadGoals();
      },
      error: (err) => {
        this.message.error('Không thể đánh dấu hoàn thành!');
        console.error(err);
      }
    });
  }
}
