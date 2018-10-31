#!/bin/bash

JSON=$(speedtest --csv)

echo JSON >> timelog
