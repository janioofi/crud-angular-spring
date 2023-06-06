import { ResolveFn } from '@angular/router';
import { Observable } from 'rxjs';
import { CoursesService } from '../service/courses.service';
import { Course } from '../model/course';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CourseResolver {

  constructor(private coursesService: CoursesService) {}

  resolve: ResolveFn<Course | Observable<Course> | Promise<Course>> = (route, state) => {
    const courseId = route.params['id'];

    if (courseId) {
      return this.coursesService.loadById(courseId);
    } else {
      return {_id: '', name: '', category: '', hours: ''};
    }
  };
}
