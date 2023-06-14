import { Lesson } from "./lesson";

export interface Course {
	_id: string;
	name: string;
	category: string;
	hours: string;
	lessons: Lesson[];
}
