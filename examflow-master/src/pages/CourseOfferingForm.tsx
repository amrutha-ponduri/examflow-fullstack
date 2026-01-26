import React, { useState, useEffect } from "react";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Button } from "@/components/ui/button";
import { useToast } from "@/hooks/use-toast";
import { Separator } from "@/components/ui/separator";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";
import {
  Popover,
  PopoverContent,
  PopoverTrigger,
} from "@/components/ui/popover";
import { Checkbox } from "@/components/ui/checkbox";
import { ChevronDown, X } from "lucide-react";
import { Badge } from "@/components/ui/badge";
import { ScrollArea } from "@/components/ui/scroll-area";

// Mock data for dropdowns
const mockDepartments = [
  { id: 1, name: "Computer Science & Engineering" },
  { id: 2, name: "Electronics & Communication" },
  { id: 3, name: "Mechanical Engineering" },
  { id: 4, name: "Civil Engineering" },
];

const mockCourses = [
  { id: 1, code: "CS101", title: "Programming Fundamentals" },
  { id: 2, code: "CS201", title: "Data Structures" },
  { id: 3, code: "CS301", title: "Database Systems" },
  { id: 4, code: "EC101", title: "Basic Electronics" },
];

const mockPrograms = [
  { id: 1, name: "B.Tech" },
  { id: 2, name: "M.Tech" },
  { id: 3, name: "MCA" },
  { id: 4, name: "MBA" },
];

const mockRegulations = [
  { id: 1, name: "R18" },
  { id: 2, name: "R20" },
  { id: 3, name: "R22" },
];

const mockUsers = [
  { id: 1, name: "Dr. John Smith" },
  { id: 2, name: "Prof. Sarah Johnson" },
  { id: 3, name: "Dr. Michael Brown" },
  { id: 4, name: "Prof. Emily Davis" },
  { id: 5, name: "Dr. Robert Wilson" },
];

interface ModuleConfig {
  id: number;
  moduleNumber: number | "";
  moduleName: string;
}

interface CourseOfferingData {
  academicYear: string;
  semester: string;
  yearOfStudy: string;
  moduleAmount: number | "";
  departmentId: string;
  courseId: string;
  programId: string;
  regulationId: string;
  submitterId: string;
  instructorIds: string[];
  moduleCount: number | "";
}

const CourseOfferingForm: React.FC = () => {
  const { toast } = useToast();
  const [formData, setFormData] = useState<CourseOfferingData>({
    academicYear: "",
    semester: "",
    yearOfStudy: "",
    moduleAmount: "",
    departmentId: "",
    courseId: "",
    programId: "",
    regulationId: "",
    submitterId: "",
    instructorIds: [],
    moduleCount: "",
  });
  const [modules, setModules] = useState<ModuleConfig[]>([]);
  const [instructorPopoverOpen, setInstructorPopoverOpen] = useState(false);

  // Handle module count changes
  useEffect(() => {
    const count = typeof formData.moduleCount === "number" ? formData.moduleCount : 0;
    if (count > 0) {
      const newModules: ModuleConfig[] = Array.from({ length: count }, (_, i) => ({
        id: i + 1,
        moduleNumber: modules[i]?.moduleNumber ?? "",
        moduleName: modules[i]?.moduleName || "",
      }));
      setModules(newModules);
    } else {
      setModules([]);
    }
  }, [formData.moduleCount]);

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;

    if (name === "moduleAmount" || name === "moduleCount") {
      const numValue = value === "" ? "" : Math.max(0, parseInt(value) || 0);
      setFormData((prev) => ({ ...prev, [name]: numValue }));
    } else {
      setFormData((prev) => ({ ...prev, [name]: value }));
    }
  };

  const handleSelectChange = (name: keyof CourseOfferingData, value: string) => {
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleInstructorToggle = (userId: string) => {
    setFormData((prev) => ({
      ...prev,
      instructorIds: prev.instructorIds.includes(userId)
        ? prev.instructorIds.filter((id) => id !== userId)
        : [...prev.instructorIds, userId],
    }));
  };

  const removeInstructor = (userId: string) => {
    setFormData((prev) => ({
      ...prev,
      instructorIds: prev.instructorIds.filter((id) => id !== userId),
    }));
  };

  const handleModuleChange = (
    moduleId: number,
    field: keyof Omit<ModuleConfig, "id">,
    value: string
  ) => {
    setModules((prev) =>
      prev.map((module) => {
        if (module.id === moduleId) {
          if (field === "moduleNumber") {
            const numValue = value === "" ? "" : Math.max(0, parseInt(value) || 0);
            return { ...module, [field]: numValue };
          }
          return { ...module, [field]: value };
        }
        return module;
      })
    );
  };

  const validateForm = (): boolean => {
    if (!formData.academicYear.trim()) {
      toast({ title: "Validation Error", description: "Academic Year is required.", variant: "destructive" });
      return false;
    }
    if (!formData.semester) {
      toast({ title: "Validation Error", description: "Semester is required.", variant: "destructive" });
      return false;
    }
    if (!formData.yearOfStudy) {
      toast({ title: "Validation Error", description: "Year of Study is required.", variant: "destructive" });
      return false;
    }
    if (formData.moduleAmount === "" || formData.moduleAmount <= 0) {
      toast({ title: "Validation Error", description: "Module Amount must be at least 1.", variant: "destructive" });
      return false;
    }
    if (!formData.departmentId) {
      toast({ title: "Validation Error", description: "Department is required.", variant: "destructive" });
      return false;
    }
    if (!formData.courseId) {
      toast({ title: "Validation Error", description: "Course is required.", variant: "destructive" });
      return false;
    }
    if (!formData.programId) {
      toast({ title: "Validation Error", description: "Program is required.", variant: "destructive" });
      return false;
    }
    if (!formData.regulationId) {
      toast({ title: "Validation Error", description: "Regulation is required.", variant: "destructive" });
      return false;
    }
    if (!formData.submitterId) {
      toast({ title: "Validation Error", description: "Submitter is required.", variant: "destructive" });
      return false;
    }
    if (formData.instructorIds.length === 0) {
      toast({ title: "Validation Error", description: "At least one instructor is required.", variant: "destructive" });
      return false;
    }

    // Validate modules if any
    for (const module of modules) {
      if (module.moduleNumber === "" || module.moduleNumber <= 0) {
        toast({ title: "Validation Error", description: `Module ${module.id}: Module number must be at least 1.`, variant: "destructive" });
        return false;
      }
      if (!module.moduleName.trim()) {
        toast({ title: "Validation Error", description: `Module ${module.id}: Module name is required.`, variant: "destructive" });
        return false;
      }
    }

    return true;
  };

  const getSubmissionData = () => ({
    ...formData,
    academicYear: formData.academicYear.trim(),
    modules: modules.map((m) => ({
      moduleNumber: m.moduleNumber,
      moduleName: m.moduleName.trim(),
    })),
  });

  const handleAdd = () => {
    if (!validateForm()) return;
    const data = getSubmissionData();
    toast({ title: "Course Offering Added", description: `Course offering for ${formData.academicYear} has been created.` });
    console.log("Add Course Offering:", data);
  };

  const handleUpdate = () => {
    if (!validateForm()) return;
    const data = getSubmissionData();
    toast({ title: "Course Offering Updated", description: `Course offering for ${formData.academicYear} has been updated.` });
    console.log("Update Course Offering:", data);
  };

  const handleView = () => {
    toast({ title: "View Mode", description: "Displaying course offering details." });
    console.log("View Course Offering:", getSubmissionData());
  };

  const handleDelete = () => {
    toast({ title: "Course Offering Deleted", description: "The course offering has been removed.", variant: "destructive" });
    console.log("Delete Course Offering");
    // Reset form
    setFormData({
      academicYear: "",
      semester: "",
      yearOfStudy: "",
      moduleAmount: "",
      departmentId: "",
      courseId: "",
      programId: "",
      regulationId: "",
      submitterId: "",
      instructorIds: [],
      moduleCount: "",
    });
    setModules([]);
  };

  const getSelectedInstructorNames = () => {
    return formData.instructorIds
      .map((id) => mockUsers.find((u) => u.id.toString() === id)?.name)
      .filter(Boolean);
  };

  return (
    <div className="min-h-screen bg-background flex items-start justify-center p-4 py-8">
      <Card className="w-full max-w-4xl shadow-md">
        <CardHeader className="pb-4">
          <CardTitle className="text-xl font-bold underline underline-offset-4 decoration-2">
            Course Offering
          </CardTitle>
        </CardHeader>
        <CardContent>
          <ScrollArea className="h-[calc(100vh-200px)] pr-4">
            <form className="space-y-8">
              {/* Academic Details Section */}
              <div className="space-y-4">
                <h3 className="text-sm font-semibold text-muted-foreground uppercase tracking-wide">
                  Academic Details
                </h3>
                <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-4">
                  <div className="space-y-2">
                    <Label htmlFor="academicYear" className="text-sm font-medium">
                      Academic Year <span className="text-destructive">*</span>
                    </Label>
                    <Input
                      id="academicYear"
                      name="academicYear"
                      type="text"
                      placeholder="e.g., 2024-25"
                      value={formData.academicYear}
                      onChange={handleInputChange}
                      className="w-full"
                    />
                  </div>

                  <div className="space-y-2">
                    <Label htmlFor="semester" className="text-sm font-medium">
                      Semester <span className="text-destructive">*</span>
                    </Label>
                    <Select
                      value={formData.semester}
                      onValueChange={(value) => handleSelectChange("semester", value)}
                    >
                      <SelectTrigger className="w-full">
                        <SelectValue placeholder="Select semester" />
                      </SelectTrigger>
                      <SelectContent className="bg-background border shadow-md z-50">
                        {[1, 2, 3, 4, 5, 6, 7, 8].map((sem) => (
                          <SelectItem key={sem} value={sem.toString()}>
                            {sem}
                          </SelectItem>
                        ))}
                      </SelectContent>
                    </Select>
                  </div>

                  <div className="space-y-2">
                    <Label htmlFor="yearOfStudy" className="text-sm font-medium">
                      Year of Study <span className="text-destructive">*</span>
                    </Label>
                    <Select
                      value={formData.yearOfStudy}
                      onValueChange={(value) => handleSelectChange("yearOfStudy", value)}
                    >
                      <SelectTrigger className="w-full">
                        <SelectValue placeholder="Select year" />
                      </SelectTrigger>
                      <SelectContent className="bg-background border shadow-md z-50">
                        {["1st", "2nd", "3rd", "4th"].map((year) => (
                          <SelectItem key={year} value={year}>
                            {year}
                          </SelectItem>
                        ))}
                      </SelectContent>
                    </Select>
                  </div>

                  <div className="space-y-2">
                    <Label htmlFor="moduleAmount" className="text-sm font-medium">
                      Module Amount <span className="text-destructive">*</span>
                    </Label>
                    <Input
                      id="moduleAmount"
                      name="moduleAmount"
                      type="number"
                      min="1"
                      placeholder="Enter amount"
                      value={formData.moduleAmount}
                      onChange={handleInputChange}
                      className="w-full"
                    />
                  </div>
                </div>
              </div>

              <Separator />

              {/* Academic Mapping Section */}
              <div className="space-y-4">
                <h3 className="text-sm font-semibold text-muted-foreground uppercase tracking-wide">
                  Academic Mapping
                </h3>
                <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
                  <div className="space-y-2">
                    <Label htmlFor="departmentId" className="text-sm font-medium">
                      Department <span className="text-destructive">*</span>
                    </Label>
                    <Select
                      value={formData.departmentId}
                      onValueChange={(value) => handleSelectChange("departmentId", value)}
                    >
                      <SelectTrigger className="w-full">
                        <SelectValue placeholder="Select department" />
                      </SelectTrigger>
                      <SelectContent className="bg-background border shadow-md z-50">
                        {mockDepartments.map((dept) => (
                          <SelectItem key={dept.id} value={dept.id.toString()}>
                            {dept.name}
                          </SelectItem>
                        ))}
                      </SelectContent>
                    </Select>
                  </div>

                  <div className="space-y-2">
                    <Label htmlFor="courseId" className="text-sm font-medium">
                      Course <span className="text-destructive">*</span>
                    </Label>
                    <Select
                      value={formData.courseId}
                      onValueChange={(value) => handleSelectChange("courseId", value)}
                    >
                      <SelectTrigger className="w-full">
                        <SelectValue placeholder="Select course" />
                      </SelectTrigger>
                      <SelectContent className="bg-background border shadow-md z-50">
                        {mockCourses.map((course) => (
                          <SelectItem key={course.id} value={course.id.toString()}>
                            {course.code} - {course.title}
                          </SelectItem>
                        ))}
                      </SelectContent>
                    </Select>
                  </div>

                  <div className="space-y-2">
                    <Label htmlFor="programId" className="text-sm font-medium">
                      Program <span className="text-destructive">*</span>
                    </Label>
                    <Select
                      value={formData.programId}
                      onValueChange={(value) => handleSelectChange("programId", value)}
                    >
                      <SelectTrigger className="w-full">
                        <SelectValue placeholder="Select program" />
                      </SelectTrigger>
                      <SelectContent className="bg-background border shadow-md z-50">
                        {mockPrograms.map((program) => (
                          <SelectItem key={program.id} value={program.id.toString()}>
                            {program.name}
                          </SelectItem>
                        ))}
                      </SelectContent>
                    </Select>
                  </div>

                  <div className="space-y-2">
                    <Label htmlFor="regulationId" className="text-sm font-medium">
                      Regulation <span className="text-destructive">*</span>
                    </Label>
                    <Select
                      value={formData.regulationId}
                      onValueChange={(value) => handleSelectChange("regulationId", value)}
                    >
                      <SelectTrigger className="w-full">
                        <SelectValue placeholder="Select regulation" />
                      </SelectTrigger>
                      <SelectContent className="bg-background border shadow-md z-50">
                        {mockRegulations.map((reg) => (
                          <SelectItem key={reg.id} value={reg.id.toString()}>
                            {reg.name}
                          </SelectItem>
                        ))}
                      </SelectContent>
                    </Select>
                  </div>
                </div>
              </div>

              <Separator />

              {/* User Assignment Section */}
              <div className="space-y-4">
                <h3 className="text-sm font-semibold text-muted-foreground uppercase tracking-wide">
                  User Assignment
                </h3>
                <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
                  <div className="space-y-2">
                    <Label htmlFor="submitterId" className="text-sm font-medium">
                      Submitter <span className="text-destructive">*</span>
                    </Label>
                    <Select
                      value={formData.submitterId}
                      onValueChange={(value) => handleSelectChange("submitterId", value)}
                    >
                      <SelectTrigger className="w-full">
                        <SelectValue placeholder="Select submitter" />
                      </SelectTrigger>
                      <SelectContent className="bg-background border shadow-md z-50">
                        {mockUsers.map((user) => (
                          <SelectItem key={user.id} value={user.id.toString()}>
                            {user.name}
                          </SelectItem>
                        ))}
                      </SelectContent>
                    </Select>
                  </div>

                  <div className="space-y-2">
                    <Label className="text-sm font-medium">
                      Instructor(s) <span className="text-destructive">*</span>
                    </Label>
                    <Popover open={instructorPopoverOpen} onOpenChange={setInstructorPopoverOpen}>
                      <PopoverTrigger asChild>
                        <Button
                          variant="outline"
                          role="combobox"
                          className="w-full justify-between font-normal h-auto min-h-10"
                        >
                          <div className="flex flex-wrap gap-1 flex-1">
                            {formData.instructorIds.length === 0 ? (
                              <span className="text-muted-foreground">Select instructors</span>
                            ) : (
                              getSelectedInstructorNames().map((name, idx) => (
                                <Badge
                                  key={idx}
                                  variant="secondary"
                                  className="mr-1 mb-1"
                                  onClick={(e) => {
                                    e.stopPropagation();
                                    const userId = formData.instructorIds[idx];
                                    removeInstructor(userId);
                                  }}
                                >
                                  {name}
                                  <X className="ml-1 h-3 w-3 cursor-pointer" />
                                </Badge>
                              ))
                            )}
                          </div>
                          <ChevronDown className="ml-2 h-4 w-4 shrink-0 opacity-50" />
                        </Button>
                      </PopoverTrigger>
                      <PopoverContent className="w-full p-2 bg-background border shadow-md z-50" align="start">
                        <div className="space-y-2">
                          {mockUsers.map((user) => (
                            <div
                              key={user.id}
                              className="flex items-center space-x-2 p-2 rounded hover:bg-muted cursor-pointer"
                              onClick={() => handleInstructorToggle(user.id.toString())}
                            >
                              <Checkbox
                                checked={formData.instructorIds.includes(user.id.toString())}
                                onCheckedChange={() => handleInstructorToggle(user.id.toString())}
                              />
                              <span className="text-sm">{user.name}</span>
                            </div>
                          ))}
                        </div>
                      </PopoverContent>
                    </Popover>
                  </div>
                </div>
              </div>

              <Separator />

              {/* Module Configuration Section */}
              <div className="space-y-4">
                <h3 className="text-sm font-semibold text-muted-foreground uppercase tracking-wide">
                  Module Configuration
                </h3>
                <div className="space-y-2 max-w-xs">
                  <Label htmlFor="moduleCount" className="text-sm font-medium">
                    Module Count
                  </Label>
                  <Input
                    id="moduleCount"
                    name="moduleCount"
                    type="number"
                    min="0"
                    placeholder="Enter number of modules"
                    value={formData.moduleCount}
                    onChange={handleInputChange}
                    className="w-full"
                  />
                </div>

                {modules.length > 0 && (
                  <div className="space-y-4 mt-4">
                    {modules.map((module) => (
                      <div
                        key={module.id}
                        className="p-4 border rounded-lg bg-muted/30 space-y-4"
                      >
                        <h4 className="text-sm font-medium text-foreground">
                          Module {module.id}
                        </h4>
                        <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
                          <div className="space-y-2">
                            <Label
                              htmlFor={`moduleNumber-${module.id}`}
                              className="text-xs font-medium"
                            >
                              Module Number <span className="text-destructive">*</span>
                            </Label>
                            <Input
                              id={`moduleNumber-${module.id}`}
                              type="number"
                              min="1"
                              placeholder="e.g., 1"
                              value={module.moduleNumber}
                              onChange={(e) =>
                                handleModuleChange(module.id, "moduleNumber", e.target.value)
                              }
                              className="w-full"
                            />
                          </div>

                          <div className="space-y-2">
                            <Label
                              htmlFor={`moduleName-${module.id}`}
                              className="text-xs font-medium"
                            >
                              Module Name <span className="text-destructive">*</span>
                            </Label>
                            <Input
                              id={`moduleName-${module.id}`}
                              type="text"
                              placeholder="e.g., Introduction to Programming"
                              value={module.moduleName}
                              onChange={(e) =>
                                handleModuleChange(module.id, "moduleName", e.target.value)
                              }
                              className="w-full"
                            />
                          </div>
                        </div>
                      </div>
                    ))}
                  </div>
                )}
              </div>

              <Separator />

              {/* Action Buttons */}
              <div className="flex flex-wrap justify-end gap-3 pt-4">
                <Button type="button" onClick={handleAdd} className="px-6">
                  Add
                </Button>
                <Button type="button" variant="secondary" onClick={handleUpdate} className="px-6">
                  Update
                </Button>
                <Button type="button" variant="outline" onClick={handleView} className="px-6">
                  View
                </Button>
                <Button type="button" variant="destructive" onClick={handleDelete} className="px-6">
                  Delete
                </Button>
              </div>
            </form>
          </ScrollArea>
        </CardContent>
      </Card>
    </div>
  );
};

export default CourseOfferingForm;
