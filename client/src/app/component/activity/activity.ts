import { Component, OnInit } from '@angular/core';
import { ActivityData, Activityservice } from '../../service/activityservice';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { timeout } from 'rxjs';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-activity',
  imports: [FormsModule, ReactiveFormsModule, DatePipe],
  templateUrl: './activity.html',
  styleUrl: './activity.css'
})
export class Activity implements OnInit{
  activityForm!: FormGroup;
  activities: any;
  successMessage: string = '';
  errorMessage: string = '';

  constructor(
    private fb: FormBuilder, 
    private activityService: Activityservice,
    private message: ToastrService
  ) {}

  ngOnInit(): void {
    this.activityForm = this.fb.group({
      caloriesBurned: [null, Validators.required],
      distance: [null, Validators.required],
      step: [null, Validators.required],
      date: [null, Validators.required]
    });

    this.getAllActivities();
  }
  getAllActivities(){
    this.activityService.getActivity().subscribe(res => {
      this.activities = res;
      console.log(this.activities);
    })
  }

  onSubmit() {
    if (this.activityForm.invalid) {
    this.message.warning('Vui lòng điền đầy đủ thông tin', 'Cảnh báo');
    return;
    }
    this.activityService.postActivity(this.activityForm.value).subscribe(res=> {
      this.successMessage = ("Thêm hoạt động thành công");
      this.message.success(this.successMessage, 'Thành công'); 

      this.activityForm.reset();
      this.getAllActivities();
    }, error => {
        this.errorMessage = 'Post thất bại: ' + (error.message || 'Không xác định');
        this.message.error(this.errorMessage, 'Lỗi');
    })
  }
}
