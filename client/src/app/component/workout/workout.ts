import { CommonModule } from '@angular/common';
import { Activityservice } from './../../service/activityservice';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-workout',
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './workout.html',
  styleUrl: './workout.css'
})
export class Workout implements OnInit{
  workoutForm!: FormGroup;
  pastWorkouts: any[] = [];

listLoaiHinhTapLuyen: any[] = [
  "Cardio",             
  "Tập sức mạnh",       
  "Tập dẻo dai",         
  "HIIT",                
  "Pilates",             
  "Nhảy múa",            
  "Bơi lội",             
  "Đạp xe",              
  "Chạy bộ",             
  "Đi bộ",                           
  "CrossFit",            
  "Chèo thuyền",        
  "Giãn cơ",            
  "Võ thuật",           
  "Thể dục dụng cụ",     
  "Leo núi",             
  "Plyometrics",
   "Yoga", 
   "Zumba",
   "Tập phục hồi chức năng",
   "Thể dục nhịp điệu",
   "Calisthenics",
   "Parkout",
   "Thiền & hít thở"
];

constructor(
  private fb: FormBuilder, 
  private activityService: Activityservice, 
  private message: ToastrService){

  }
  ngOnInit(): void {
    this.workoutForm = this.fb.group({
      type:  [null, Validators.required],
      duration: [null, Validators.required],
      date: [null, Validators.required],
      caloriesBurned: [null, Validators.required],
    });
    this.loadPastWorkouts();
  }
  onSubmit(){
    if (this.workoutForm.invalid) {
      this.message.warning('Vui lòng điền đầy đủ thông tin!');
      return;
    }


    this.activityService.postWorkout(this.workoutForm.value).subscribe(res => {
      this.message.success('Gửi bài tập thành công!');
      this.workoutForm.reset();
      this.loadPastWorkouts();
    }, error => {
        this.message.error('Lỗi khi gửi bài tập!');
        console.error(error);
    })
  }

    loadPastWorkouts(): void {
    this.activityService.getWorkouts().subscribe({
      next: (res) => {
        this.pastWorkouts = res;
      },
      error: (err) => {
        console.error('Lỗi tải danh sách workouts:', err);
      }
    });
  }
}
