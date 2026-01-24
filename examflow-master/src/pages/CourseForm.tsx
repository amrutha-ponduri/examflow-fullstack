import { useState } from 'react';
import { Button } from '@/components/ui/button';
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { useToast } from '@/hooks/use-toast';

const CourseForm = () => {
  const { toast } = useToast();
  const [courseCode, setCourseCode] = useState('');
  const [courseTitle, setCourseTitle] = useState('');
  const [credits, setCredits] = useState('');

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    
    if (!courseCode || !courseTitle || !credits) {
      toast({
        title: 'Validation Error',
        description: 'Please fill in all fields',
        variant: 'destructive',
      });
      return;
    }

    toast({
      title: 'Course Added',
      description: `${courseTitle} (${courseCode}) has been added successfully.`,
    });

    // Reset form
    setCourseCode('');
    setCourseTitle('');
    setCredits('');
  };

  return (
    <div className="min-h-screen bg-background flex items-center justify-center p-4">
      <Card className="w-full max-w-md shadow-lg">
        <CardHeader className="pb-4">
          <CardTitle className="text-xl font-bold text-foreground">Course</CardTitle>
        </CardHeader>
        <CardContent>
          <form onSubmit={handleSubmit} className="space-y-5">
            <div className="space-y-2">
              <Label htmlFor="courseCode" className="text-muted-foreground">
                Course Code
              </Label>
              <Input
                id="courseCode"
                type="text"
                placeholder="e.g., CS101"
                value={courseCode}
                onChange={(e) => setCourseCode(e.target.value)}
              />
            </div>

            <div className="space-y-2">
              <Label htmlFor="courseTitle" className="text-muted-foreground">
                Course Title
              </Label>
              <Input
                id="courseTitle"
                type="text"
                placeholder="e.g., Introduction to Computer Science"
                value={courseTitle}
                onChange={(e) => setCourseTitle(e.target.value)}
              />
            </div>

            <div className="space-y-2">
              <Label htmlFor="credits" className="text-muted-foreground">
                Credits
              </Label>
              <Input
                id="credits"
                type="number"
                min="1"
                max="10"
                placeholder="e.g., 3"
                value={credits}
                onChange={(e) => setCredits(e.target.value.replace(/\D/g, ''))}
              />
              <p className="text-xs text-muted-foreground">number</p>
            </div>

            <div className="flex justify-end pt-4">
              <Button type="submit">
                Add Course
              </Button>
            </div>
          </form>
        </CardContent>
      </Card>
    </div>
  );
};

export default CourseForm;
