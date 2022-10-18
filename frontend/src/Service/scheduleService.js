import callApi from "../util/api";

function createSchedule(schedule) {
    return callApi("schedule", "POST", null, schedule, true).then(res => res.data);
}

function createScheduleDetail(scheduleDetail) {
    return callApi("schedule/detail", "POST", null, scheduleDetail, true).then(res => res.data);
}

export { createSchedule, createScheduleDetail }