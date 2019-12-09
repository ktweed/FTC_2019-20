/* Copyright (c) 2019 Keith Tweed. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * The name of "Keith Tweed" nor "System32" may be used to endorse or promote
 * products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Autonomous4.byCrater", group="Pushbot")
@Disabled
public class Autonomous4 extends LinearOpMode {

    /* Declare OpMode members. */
    private ElapsedTime     runtime = new ElapsedTime();

    // Declare Variables
    private DcMotor leftRear = null;
    private DcMotor leftFront = null;
    private DcMotor leftMiddle = null;
    private DcMotor rightRear = null;
    private DcMotor rightFront = null;
    private DcMotor rightMiddle = null;
    private Servo arm = null;
    private double dozer = 1;
    private double leftPower = 1;
    private double rightPower = 1;

    static final double     FORWARD_SPEED = 1;
    static final double     TURN_SPEED    = 0.5;
    static final double     SPEED = 2.0; // Speed in feet per second

    @Override
    public void runOpMode() {

        // Initialize Hardware
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftMiddle = hardwareMap.get(DcMotor.class, "leftMiddle");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightMiddle = hardwareMap.get(DcMotor.class, "rightMiddle");
        arm = hardwareMap.get(Servo.class, "arm");



        leftRear.setDirection(DcMotorSimple.Direction.REVERSE);
        leftMiddle.setDirection(DcMotorSimple.Direction.REVERSE);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);

        // Initialize arm position
        arm.setPosition(1);

        // Telemetry Update
        telemetry.addData("Status", "Initialized");

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step through each leg of the path, ensuring that the Auto mode has not been stopped along the way

        // Step 1:  Drive forward
        leftPower = 1;
        rightPower = 1;

        leftRear.setPower(leftPower);
        leftMiddle.setPower(leftPower);
        leftFront.setPower(leftPower);
        rightRear.setPower(rightPower);
        rightMiddle.setPower(rightPower);
        rightFront.setPower(rightPower);

        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.8)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        leftPower = 0;
        rightPower = 0;

        leftRear.setPower(leftPower);
        leftMiddle.setPower(leftPower);
        leftFront.setPower(leftPower);
        rightRear.setPower(rightPower);
        rightMiddle.setPower(rightPower);
        rightFront.setPower(rightPower);


        // Step 2:  Spin left
        leftPower = -TURN_SPEED;
        rightPower = TURN_SPEED;

        leftRear.setPower(leftPower);
        leftMiddle.setPower(leftPower);
        leftFront.setPower(leftPower);
        rightRear.setPower(rightPower);
        rightMiddle.setPower(rightPower);
        rightFront.setPower(rightPower);

        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.9)) {
            telemetry.addData("Path", "Leg 2: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();

        }

        leftPower = 0;
        rightPower = 0;

        leftRear.setPower(leftPower);
        leftMiddle.setPower(leftPower);
        leftFront.setPower(leftPower);
        rightRear.setPower(rightPower);
        rightMiddle.setPower(rightPower);
        rightFront.setPower(rightPower);


        // Step 3:  Drive Forwards

        leftPower = FORWARD_SPEED;
        rightPower = FORWARD_SPEED;

        leftRear.setPower(leftPower);
        leftMiddle.setPower(leftPower);
        leftFront.setPower(leftPower);
        rightRear.setPower(rightPower);
        rightMiddle.setPower(rightPower);
        rightFront.setPower(rightPower);

        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.5)) {
            telemetry.addData("Path", "Leg 4: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        arm.setPosition(1);

        // Step 4:  Spin left
        leftPower = -TURN_SPEED;
        rightPower = TURN_SPEED;

        leftRear.setPower(leftPower);
        leftMiddle.setPower(leftPower);
        leftFront.setPower(leftPower);
        rightRear.setPower(rightPower);
        rightMiddle.setPower(rightPower);
        rightFront.setPower(rightPower);

        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.4)) {
            telemetry.addData("Path", "Leg 2: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();

        }



        leftPower = 0;
        rightPower = 0;

        leftRear.setPower(leftPower);
        leftMiddle.setPower(leftPower);
        leftFront.setPower(leftPower);
        rightRear.setPower(rightPower);
        rightMiddle.setPower(rightPower);
        rightFront.setPower(rightPower);

        // Step 5:  Drive Forwards

        leftPower = FORWARD_SPEED;
        rightPower = FORWARD_SPEED;

        leftRear.setPower(leftPower);
        leftMiddle.setPower(leftPower);
        leftFront.setPower(leftPower);
        rightRear.setPower(rightPower);
        rightMiddle.setPower(rightPower);
        rightFront.setPower(rightPower);

        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.2)) {
            telemetry.addData("Path", "Leg 4: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        leftPower = 0;
        rightPower = 0;

        leftRear.setPower(leftPower);
        leftMiddle.setPower(leftPower);
        leftFront.setPower(leftPower);
        rightRear.setPower(rightPower);
        rightMiddle.setPower(rightPower);
        rightFront.setPower(rightPower);

        arm.setPosition(1);

        while (opModeIsActive() && (runtime.seconds() < 1.2)) {
            telemetry.addData("Path", "Leg 4: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        // Step 5.5:  Spin left
        leftPower = TURN_SPEED;
        rightPower = -TURN_SPEED;

        leftRear.setPower(leftPower);
        leftMiddle.setPower(leftPower);
        leftFront.setPower(leftPower);
        rightRear.setPower(rightPower);
        rightMiddle.setPower(rightPower);
        rightFront.setPower(rightPower);

        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.4)) {
            telemetry.addData("Path", "Leg 2: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();

        }



        leftPower = 0;
        rightPower = 0;

        leftRear.setPower(leftPower);
        leftMiddle.setPower(leftPower);
        leftFront.setPower(leftPower);
        rightRear.setPower(rightPower);
        rightMiddle.setPower(rightPower);
        rightFront.setPower(rightPower);

        // Step 5.6:  Drive Forwards

        leftPower = FORWARD_SPEED;
        rightPower = FORWARD_SPEED;

        leftRear.setPower(leftPower);
        leftMiddle.setPower(leftPower);
        leftFront.setPower(leftPower);
        rightRear.setPower(rightPower);
        rightMiddle.setPower(rightPower);
        rightFront.setPower(rightPower);

        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.0)) {
            telemetry.addData("Path", "Leg 4: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        leftPower = 0;
        rightPower = 0;

        leftRear.setPower(leftPower);
        leftMiddle.setPower(leftPower);
        leftFront.setPower(leftPower);
        rightRear.setPower(rightPower);
        rightMiddle.setPower(rightPower);
        rightFront.setPower(rightPower);

        arm.setPosition(1);

        // Step 6: Drop Marker

        arm.setPosition(0.3);

        // Step 7: Reverse!

        arm.setPosition(0.3);

        leftPower = -FORWARD_SPEED;
        rightPower = -FORWARD_SPEED;

        leftRear.setPower(leftPower);
        leftMiddle.setPower(leftPower);
        leftFront.setPower(leftPower);
        rightRear.setPower(rightPower);
        rightMiddle.setPower(rightPower);
        rightFront.setPower(rightPower);

        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.5)) {
            telemetry.addData("Path", "Leg 3: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        leftPower = 0;
        rightPower = 0;

        leftRear.setPower(leftPower);
        leftMiddle.setPower(leftPower);
        leftFront.setPower(leftPower);
        rightRear.setPower(rightPower);
        rightMiddle.setPower(rightPower);
        rightFront.setPower(rightPower);



        // Step 8 - U-turn

        leftPower = -TURN_SPEED;
        rightPower = -TURN_SPEED;

        leftRear.setPower(leftPower);
        leftMiddle.setPower(leftPower);
        leftFront.setPower(leftPower);
        rightRear.setPower(rightPower);
        rightMiddle.setPower(rightPower);
        rightFront.setPower(rightPower);

        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.0)) {
            telemetry.addData("Path", "Leg 2: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();

        }

        arm.setPosition(1);

        leftPower = -TURN_SPEED;
        rightPower = TURN_SPEED;

        leftRear.setPower(leftPower);
        leftMiddle.setPower(leftPower);
        leftFront.setPower(leftPower);
        rightRear.setPower(rightPower);
        rightMiddle.setPower(rightPower);
        rightFront.setPower(rightPower);

        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 2.0)) {
            telemetry.addData("Path", "Leg 2: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();

        }


        // Step 9 - "Enter" crater

        leftPower = FORWARD_SPEED;
        rightPower = FORWARD_SPEED;

        leftRear.setPower(leftPower);
        leftMiddle.setPower(leftPower);
        leftFront.setPower(leftPower);
        rightRear.setPower(rightPower);
        rightMiddle.setPower(rightPower);
        rightFront.setPower(rightPower);

        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 2.3)) {
            telemetry.addData("Path", "Leg 3: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        leftPower = 0;
        rightPower = 0;

        leftRear.setPower(leftPower);
        leftMiddle.setPower(leftPower);
        leftFront.setPower(leftPower);
        rightRear.setPower(rightPower);
        rightMiddle.setPower(rightPower);
        rightFront.setPower(rightPower);

        // Step 4: Stop
        leftPower = 0;
        rightPower = 0;

        leftRear.setPower(leftPower);
        leftMiddle.setPower(leftPower);
        leftFront.setPower(leftPower);
        rightRear.setPower(rightPower);
        rightMiddle.setPower(rightPower);
        rightFront.setPower(rightPower);

        arm.setPosition(0);

        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);

    }
}
