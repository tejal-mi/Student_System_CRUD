package in.strikes.crudSpringBootDemo.Services;

import in.strikes.crudSpringBootDemo.Entity.Student;
import in.strikes.crudSpringBootDemo.Repositories.StudentRepo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepo studentRepo;

    public StudentService(StudentRepo studentRepo){
        this.studentRepo= studentRepo;
    }
    public Student createStudent(Student studentReq){
        Student studentResponse = studentRepo.save(studentReq);
        return studentResponse;
    }

    public Student getStudent(Long id){
        Optional<Student> studentResp = studentRepo.findById(id);
        if(studentResp.isPresent()) return studentResp.get();
        else{
            return null;
        }
    }

    public List<Student> getAllStudent(){
        List<Student> studentResp = studentRepo.findAll();
        return studentResp;
    }

    public Student updateStudent(Long id, Student studentReq){
        Optional<Student> existingStudent = studentRepo.findById(id);
        if(existingStudent.isEmpty())
            return null;
        Student studentToSave = existingStudent.get();
        studentToSave.setAge(studentReq.getAge());
        studentToSave.setEmail(studentReq.getEmail());
        studentToSave.setRollNo(studentReq.getRollNo());
        studentToSave.setName(studentReq.getName());
        studentToSave.setSubject(studentReq.getSubject());

        return studentRepo.save(studentToSave);
    }

    public boolean deleteStudent(Long id){
        Boolean isStudent = studentRepo.existsById(id);
        if(!isStudent) return false;

        studentRepo.deleteById(id);
        return true;
    }
}
