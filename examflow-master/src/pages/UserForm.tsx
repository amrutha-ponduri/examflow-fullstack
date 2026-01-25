import { useState } from 'react';
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { Button } from '@/components/ui/button';
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from '@/components/ui/select';
import { useToast } from '@/hooks/use-toast';
import { Eye, EyeOff } from 'lucide-react';

interface UserData {
  username: string;
  password: string;
  role: string;
  name: string;
}

const UserForm = () => {
  const { toast } = useToast();
  const [showPassword, setShowPassword] = useState(false);
  const [formData, setFormData] = useState<UserData>({
    username: '',
    password: '',
    role: '',
    name: '',
  });

  const handleInputChange = (field: keyof UserData, value: string) => {
    setFormData((prev) => ({ ...prev, [field]: value }));
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();

    if (!formData.username.trim() || !formData.password.trim() || !formData.role || !formData.name.trim()) {
      toast({
        title: 'Validation Error',
        description: 'Please fill in all fields',
        variant: 'destructive',
      });
      return;
    }

    if (formData.password.length < 4) {
      toast({
        title: 'Validation Error',
        description: 'Password must be at least 4 characters',
        variant: 'destructive',
      });
      return;
    }

    toast({
      title: 'User Added',
      description: `User "${formData.username}" has been created successfully.`,
    });

    // Reset form
    setFormData({ username: '', password: '', role: '', name: '' });
  };

  return (
    <div className="min-h-screen bg-background flex items-center justify-center p-4">
      <Card className="w-full max-w-md shadow-md">
        <CardHeader className="pb-4">
          <CardTitle className="text-xl font-bold text-foreground underline underline-offset-4">
            User
          </CardTitle>
        </CardHeader>
        <CardContent>
          <form onSubmit={handleSubmit} className="space-y-5">
            <div className="space-y-2">
              <Label htmlFor="username" className="text-muted-foreground">
                Username
              </Label>
              <Input
                id="username"
                type="text"
                placeholder="Enter username"
                value={formData.username}
                onChange={(e) => handleInputChange('username', e.target.value)}
              />
            </div>

            <div className="space-y-2">
              <Label htmlFor="password" className="text-muted-foreground">
                Password
              </Label>
              <div className="relative">
                <Input
                  id="password"
                  type={showPassword ? 'text' : 'password'}
                  placeholder="Enter password"
                  value={formData.password}
                  onChange={(e) => handleInputChange('password', e.target.value)}
                  className="pr-10"
                />
                <button
                  type="button"
                  onClick={() => setShowPassword(!showPassword)}
                  className="absolute right-3 top-1/2 -translate-y-1/2 text-muted-foreground hover:text-foreground transition-colors"
                  aria-label={showPassword ? 'Hide password' : 'Show password'}
                >
                  {showPassword ? <EyeOff size={18} /> : <Eye size={18} />}
                </button>
              </div>
            </div>

            <div className="space-y-2">
              <Label htmlFor="role" className="text-muted-foreground">
                Role
              </Label>
              <Select
                value={formData.role}
                onValueChange={(value) => handleInputChange('role', value)}
              >
                <SelectTrigger id="role" className="w-full bg-background">
                  <SelectValue placeholder="Select role" />
                </SelectTrigger>
                <SelectContent className="bg-background border shadow-md z-50">
                  <SelectItem value="admin">Admin</SelectItem>
                  <SelectItem value="exam_cell">Exam Cell Faculty</SelectItem>
                  <SelectItem value="faculty">Faculty</SelectItem>
                </SelectContent>
              </Select>
            </div>

            <div className="space-y-2">
              <Label htmlFor="name" className="text-muted-foreground">
                Name
              </Label>
              <Input
                id="name"
                type="text"
                placeholder="Enter full name"
                value={formData.name}
                onChange={(e) => handleInputChange('name', e.target.value)}
              />
            </div>

            <div className="flex justify-end pt-2">
              <Button type="submit">Add User</Button>
            </div>
          </form>
        </CardContent>
      </Card>
    </div>
  );
};

export default UserForm;
