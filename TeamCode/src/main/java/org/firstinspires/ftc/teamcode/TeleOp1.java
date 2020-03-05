package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="TeleOp1", group="Linear Opmode")

public class TeleOp1 extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor leftRear = null;
    private DcMotor leftFront = null;
    private DcMotor rightRear = null;
    private DcMotor rightFront = null;
    private DcMotor dcArm = null;
    private DcMotor dcGrab = null;

    private boolean rbPress = false;
    private boolean lbPress = false;
    private boolean lsPress = false;
    private boolean rsPress = false;

    private int grabPosLast = 0;

    private int grabPos = 0;
    private int armPos = 0;

    private DeviceInterfaceModule cdi;
    private ModernRoboticsI2cColorSensor color;

    private boolean leftTurn = false;
    private boolean rightTurn = false;
    private boolean leftBrake = false;
    private boolean rightBrake = false;

    private boolean grabLock = false;
    private boolean armLock = false;

    int ledFix = 0;
    int ledBlink = 0;

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

        telemetry.addData("Motors", "Ready");
        telemetry.update();

        color = hardwareMap.get(ModernRoboticsI2cColorSensor.class, "color");
        cdi = hardwareMap.deviceInterfaceModule.get("Device Interface Module 1");

        telemetry.addData("Motors", "Ready");
        telemetry.addData("Device Interface", "Ready");
        telemetry.update();

        leftLed = hardwareMap.get(DigitalChannel.class, "leftLed");
        rightLed = hardwareMap.get(DigitalChannel.class, "rightLed");
        backLeds = hardwareMap.get(DigitalChannel.class, "backLeds");

        leftLed.setMode(DigitalChannel.Mode.OUTPUT);
        rightLed.setMode(DigitalChannel.Mode.OUTPUT);
        backLeds.setMode(DigitalChannel.Mode.OUTPUT);

        telemetry.addData("Motors", "Ready");
        telemetry.addData("Device Interface", "Ready");
        telemetry.addData("Lighting", "Ready");
        telemetry.update();

        rightRear.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);

        dcArm = hardwareMap.get(DcMotor.class, "dcArm");
        dcArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        dcArm.setPower(1);
        dcArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        dcGrab = hardwareMap.get(DcMotor.class, "dcGrab");
        dcGrab.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        dcGrab.setPower(1);
        dcGrab.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        telemetry.addData("Status", "Initialized");
        telemetry.update();



        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {


            // Left Side Tank Control

            if (gamepad1.left_stick_y < 0.2) {

                // leftRear.setPower(1);
                // leftFront.setPower(1);

                leftRear.setPower(gamepad1.left_stick_y);
                leftFront.setPower(gamepad1.left_stick_y);

                leftBrake = false;

            }

            if (gamepad1.left_stick_y > -0.2) {

                // leftRear.setPower(-1);
                // leftFront.setPower(-1);

                leftRear.setPower(gamepad1.left_stick_y);
                leftFront.setPower(gamepad1.left_stick_y);

                leftBrake = false;

            }

            if (gamepad1.left_stick_y < 0.2) {

                if (gamepad1.left_stick_y > -0.2) {

                    leftRear.setPower(0);
                    leftFront.setPower(0);

                    leftBrake = true;
                }

            }


            // Right Side Tank Control

            if (gamepad1.right_stick_y < 0.2) {

                // rightRear.setPower(1);
                // rightFront.setPower(1);

                rightRear.setPower(gamepad1.right_stick_y);
                rightFront.setPower(gamepad1.right_stick_y);

                rightBrake = false;
            }

            if (gamepad1.right_stick_y > -0.2) {

                // rightRear.setPower(-1);
                // rightFront.setPower(-1);

                rightRear.setPower(gamepad1.right_stick_y);
                rightFront.setPower(gamepad1.right_stick_y);

                rightBrake = false;
            }

            if (gamepad1.right_stick_y < 0.2) {

                if (gamepad1.right_stick_y > -0.2) {

                    rightRear.setPower(0);
                    rightFront.setPower(0);

                    rightBrake = true;
                }

            }

            if (gamepad1.x) {
                telemetry.addData("Red", color.red());
                telemetry.addData("Green", color.green());
                telemetry.addData("Blue", color.blue());
            }

            if (gamepad1.y) {
                telemetry.addData("Encoder Position", dcArm.getCurrentPosition());
                telemetry.addData("Power", dcArm.getPower());
            }

            if (gamepad1.dpad_up) {
                dcArm.setTargetPosition(0);
            } else if (gamepad1.dpad_down) {
                dcArm.setTargetPosition(1400);
            }

            if (gamepad1.dpad_left) {
                dcArm.setPower(0);
            } else if (gamepad1.dpad_right) {
                dcArm.setPower(1);
            }

            if (gamepad1.a) {
                dcArm.setTargetPosition(1800);
            }

                if (gamepad1.left_bumper && !lbPress) {
                    leftTurn = !leftTurn;
                    lbPress = true;
                }

                if (!gamepad1.left_bumper) {
                    lbPress = false;
                }

                if (gamepad1.right_bumper && !rbPress) {
                    rightTurn = !rightTurn;
                    rbPress = true;
                }

                if (!gamepad1.right_bumper) {
                    rbPress = false;
                }

            if (leftTurn) {
                if (ledBlink < 3000) {
                    leftLed.setState(true);
                } else {
                    leftLed.setState(false);
                }
            } else {leftLed.setState(false);}

            if (rightTurn) {
                if (ledBlink < 3000) {
                    rightLed.setState(true);
                } else {
                    rightLed.setState(false);
                }
            } else {rightLed.setState(false);}

            if (leftBrake & rightBrake) {
                cdi.setLED(1, true);
                backLeds.setState(true);
            } else {
                cdi.setLED(1, false);
                backLeds.setState(false);
            }

            telemetry.addData("Trigger Position", gamepad1.left_trigger);

            ledBlink++;

            if (ledBlink > 6000) {
                ledBlink = 0;
            }

            if (gamepad1.left_stick_button && !lsPress) {
                grabLock = !grabLock;
                grabPos = dcGrab.getCurrentPosition();
                lsPress = true;
            }

            if (!gamepad1.left_stick_button) {
                lsPress = false;
            }

            if (gamepad1.right_stick_button && !rsPress) {
                armLock = !armLock;
                armPos = dcArm.getCurrentPosition();
                rsPress = true;
            }

            if (!gamepad1.right_stick_button) {
                rsPress = false;
            }


            if (!grabLock) {
                dcGrab.setTargetPosition((int) (gamepad1.left_trigger * 2100));
                dcGrab.setPower(1);
            } else {
                dcGrab.setTargetPosition(grabPos);
            }

            if (!armLock) {
                if (gamepad1.right_trigger > 0.1) {
                    dcArm.setTargetPosition((int) (1600 - (gamepad1.right_trigger * 1600)));
                }
            } else {
                dcArm.setTargetPosition(armPos);
            }

            telemetry.update();

            dcArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            dcGrab.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            if (gamepad1.left_stick_button) {
                dcGrab.setPower(0);
            }
        }
    }
}