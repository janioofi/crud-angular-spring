import { Lesson } from './../../model/lesson';
import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, NonNullableFormBuilder, UntypedFormArray, Validators } from '@angular/forms';
import { CoursesService } from '../../service/courses.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Course } from '../../model/course';
import { FormUtilsService } from 'src/app/shared/form/form-utils.service';

@Component({
	selector: 'app-course-form',
	templateUrl: './course-form.component.html',
	styleUrls: ['./course-form.component.scss'],
})
export class CourseFormComponent implements OnInit {

	form!: FormGroup;

	constructor(
		private formBuilder: NonNullableFormBuilder,
		private service: CoursesService,
		private snackBar: MatSnackBar,
		private locaton: Location,
		private route: ActivatedRoute,
		public formUtils: FormUtilsService
	) {}

	ngOnInit(): void {
		const course: Course = this.route.snapshot.data['course']
		this.form = this.formBuilder.group({
			_id: [course._id],
			name: [course.name, [Validators.required, Validators.minLength(3), Validators.maxLength(100)]],
			category: [course.category,[Validators.required]],
			hours: [course.hours, [Validators.required, Validators.min(1), Validators.max(500)]],
			lessons: this.formBuilder.array(this.retrieveLessons(course), Validators.required)
		})
	}

	private retrieveLessons(course: Course) {
		const lessons = [];
		if(course?.lessons){
			course.lessons.forEach(lesson => lessons.push(this.createLesson(lesson)));
		} else {
			lessons.push(this.createLesson());
		}
		return lessons;
	}

	private createLesson(lesson: Lesson = {_id: '', name: '', youtubeUrl: ''}){
		return this.formBuilder.group({
			_id: [lesson._id],
			name: [lesson.name, [Validators.required, Validators.minLength(3), Validators.maxLength(100)]],
			youtubeUrl:[lesson.youtubeUrl, [Validators.required, Validators.minLength(10), Validators.maxLength(11)]]
		});
	}

	getLessonsFormArray(){
		return (<UntypedFormArray>this.form.get('lessons')).controls;
	}

	addNewLesson(){
		const lessons = this.form.get('lessons') as UntypedFormArray;
		lessons.push(this.createLesson());
	}

	removeLesson(index: number){
		const lessons = this.form.get('lessons') as UntypedFormArray;
		lessons.removeAt(index);
	}

	onSubmit() {
		if(this.form.valid){
			this.service.save(this.form.value).subscribe(
				(result) => this.onSuccess(),
				(error) => this.onError()
			);
		}else{
			this.formUtils.validadeAllFormFields(this.form);
		}
	}

	private onError() {
		this.snackBar.open('Erro ao salvar curso', '', { duration: 5000 });
	}

	private onSuccess() {
		this.snackBar.open('Curso salvo com sucesso', '', { duration: 5000 });
		this.onCancel();
	}

	onCancel() {
		this.locaton.back();
	}
}
