//下拉菜单（按钮）

function Dropdown($dropdown) {
    this._dom = $dropdown;
    this.name = this._dom.attr("data-dropdown");
    this.DropdownList = new DropdownList(this._dom.children("div.option-list"));
    this.isExpand = this._dom.hasClass("expand");
    if (this.isExpand) {
        this.expand();
    } else {
        this.collapse();
    }
}

Dropdown.prototype.collapse = function () {
    this._dom.removeClass("expand");
    this.isExpand = false;
}

Dropdown.prototype.expand = function () {
    this._dom.addClass("expand");
    this.isExpand = true;
}

Dropdown.prototype.toggle = function () {
    if (this.isExpand) {
        this.collapse();
    } else {
        this.expand();
    }
}



//下拉菜单（下拉菜单组成的块）

function DropdownList($dropdown) {
    this._dom = $dropdown;
}

DropdownList.prototype.isParentOf = function ($dom) {
    return this._dom.find($dom).length > 0;
}


//初始化

Dropdown.Initialize = function () {
    $("div[data-dropdown] .option-value").click(function () {
        var dropDown = new Dropdown($(this).parent());
        dropDown.toggle();

        var expandedDropDown = Dropdown.expandedDropDown;
        if (expandedDropDown && expandedDropDown != dropDown) {
            expandedDropDown.collapse();
        }
        if (dropDown.isExpand) {
            Dropdown.expandedDropDown = dropDown;
        } else {
            Dropdown.expandedDropDown = null;
        }
    });

    $(window).click(function () {
        var target = $(event.target);
        if ($("div[data-dropdown]").find($(target)).length > 0) {
            return;
        }

        var expandedDropDown = Dropdown.expandedDropDown;
        if (!expandedDropDown) {
            return;
        }
        if (expandedDropDown.DropdownList.isParentOf(target)) {
            return;//点击的是已展开的菜单的项
        }

        if (targetIsDatePicker(target)) {
            return; //点击的是日期选择控件
        }

        expandedDropDown.collapse();
        Dropdown.expandedDropDown = null;
    });
}

function targetIsDatePicker($target) {
    var datePicker = $("div.pika-single");
    if (datePicker.find($target).length > 0) {
        return true;
    }
    return false;
}