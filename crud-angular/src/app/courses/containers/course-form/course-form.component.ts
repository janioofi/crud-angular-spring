import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, NonNullableFormBuilder } from '@angular/forms';
import { CoursesService } from '../../service/courses.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Course } from '../../model/course';

@Component({
	selector: 'app-course-form',
	templateUrl: './course-form.component.html',
	styleUrls: ['./course-form.component.scss'],
})
export class CourseFormComponent implements OnInit {

	form = this.formBuilder.group({
		_id: [''],
		name: [''],
		category: [''],
		hours: [''],
	});

	constructor(
		private formBuilder: NonNullableFormBuilder,
		private service: CoursesService,
		private snackBar: MatSnackBar,
		private locaton: Location,
		private route: ActivatedRoute
	) {}

	ngOnInit(): void {
		const course: Course = this.route.snapshot.data['course']
		this.form.setValue({
			_id: course._id,
			name: course.name,
			category: course.category,
			hours: course.hours
		})
	}

	onSubmit() {
		this.service.save(this.form.value).subscribe(
			(result) => this.onSuccess(),
			(error) => this.onError()
		);
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
