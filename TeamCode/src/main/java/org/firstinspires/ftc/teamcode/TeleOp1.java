package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.DigitalChannel;

@TeleOp(name="TeleOp1", group="Linear Opmode")

public class TeleOp1 extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor leftRear = null;
    private DcMotor leftFront = null;
    private DcMotor rightRear = null;
    private DcMotor rightFront = null;

    private DeviceInterfaceModule cdi;
    private ModernRoboticsI2cColorSensor color;

    private boolean leftTurn = false;
    private boolean rightTurn = false;
    private boolean leftBrake = false;
    private boolean rightBrake = false;

    DigitalChannel leftLed;
    DigitalChannel rightLed;
    DigitalChannel backLeds;

    @Override
    public void runOpMode() {

        // Initialize Hardware
        leftRear = hardwareMap.get(DcMotor.class, "backLeft");
        leftFront = hardwareMap.get(DcMotor.class, "frontLeft");
        rightRear = hardwareMap.get(DcMotor.class, "backRight");
        rightFront = hardwareMap.get(DcMotor.class, "frontRight");
        color = hardwareMap.get(ModernRoboticsI2cColorSensor.class, "color");
        cdi = hardwareMap.deviceInterfaceModule.get("Device Interface Module 1");

        leftLed = hardwareMap.get(DigitalChannel.class, "leftLed");
        rightLed = hardwareMap.get(DigitalChannel.class, "rightLed");
        backLeds = hardwareMap.get(DigitalChannel.class, "backLeds");

        leftLed.setMode(DigitalChannel.Mode.OUTPUT);
        rightLed.setMode(DigitalChannel.Mode.OUTPUT);
        backLeds.setMode(DigitalChannel.Mode.OUTPUT);

        rightRear.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);

        //
        telemetry.addData("Status", "Initialized");
        telemetry.update();



        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {


            // Left Side Tank Control

            if (gamepad1.left_stick_y < 0.1) {

                // leftRear.setPower(1);
                // leftFront.setPower(1);

                leftRear.setPower(gamepad1.left_stick_y);
                leftFront.setPower(gamepad1.left_stick_y);

                leftBrake = false;

            }

            if (gamepad1.left_stick_y > -0.1) {

                // leftRear.setPower(-1);
                // leftFront.setPower(-1);

                leftRear.setPower(gamepad1.left_stick_y);
                leftFront.setPower(gamepad1.left_stick_y);

                leftBrake = false;

            }

            if (gamepad1.left_stick_y < 0.1) {

                if (gamepad1.left_stick_y > -0.1) {

                    leftRear.setPower(0);
                    leftFront.setPower(0);

                    leftBrake = true;
                }

            }


            // Right Side Tank Control

            if (gamepad1.right_stick_y < 0.1) {

                // rightRear.setPower(1);
                // rightFront.setPower(1);

                rightRear.setPower(gamepad1.right_stick_y);
                rightFront.setPower(gamepad1.right_stick_y);

                rightBrake = false;
            }

            if (gamepad1.right_stick_y > -0.1) {

                // rightRear.setPower(-1);
                // rightFront.setPower(-1);

                rightRear.setPower(gamepad1.right_stick_y);
                rightFront.setPower(gamepad1.right_stick_y);

                rightBrake = false;
            }

            if (gamepad1.right_stick_y < 0.1) {

                if (gamepad1.right_stick_y > -0.1) {

                    rightRear.setPower(0);
                    rightFront.setPower(0);

                    rightBrake = true;
                }

            }

            if (gamepad1.x) {
                telemetry.addData("Red: ", color.red());
                telemetry.addData("Green: ", color.green());
                telemetry.addData("Blue: ", color.blue());
                telemetry.update();
            }

            if (gamepad1.left_bumper) {
                leftTurn = !leftTurn;
            }

            if (gamepad1.right_bumper) {
                rightTurn = !rightTurn;
            }

            if (leftTurn) {
                leftLed.setState(true);
            } else {leftLed.setState(false);}

            if (rightTurn) {
                rightLed.setState(true);
            } else {rightLed.setState(false);}

            if (leftBrake & rightBrake) {
                cdi.setLED(1, true);
                backLeds.setState(true);
            } else {
                cdi.setLED(1, false);
                backLeds.setState(false);
            }
        }
    }
}

