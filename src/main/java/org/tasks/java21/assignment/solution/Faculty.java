package org.tasks.java21.assignment.solution;

abstract public sealed class Faculty implements Educational permits
    EngineeringFaculty, HumanitiesFaculty, BusinessFaculty {

}
